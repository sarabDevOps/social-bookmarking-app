package bgjobs;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import dao.BookmarkDao;
import entities.WebLink;
import util.HttpConnect;
import util.IOUtil;




public class WebpageDownloaderTask implements Runnable {

	private static BookmarkDao dao = new BookmarkDao();

	private static final long TIME_FRAME = 3000000000L; // 3 seconds

	private boolean downloadAll = false;

	// Executor - At any given time there will be 5 Threads
	ExecutorService downloadExecutor = Executors.newFixedThreadPool(5);
	
	private static class Downloader<T extends WebLink> implements Callable<T> {
		private T weblink;
		public Downloader(T weblink) {
			this.weblink = weblink;
		}
		public T call() {
			try {
				if(!weblink.getUrl().endsWith(".pdf")){
					weblink.setDownloadStatus(WebLink.DownloadStatus.FAILED);
					String htmlPage = HttpConnect.download(weblink.getUrl());
					weblink.setHtmlPage(htmlPage);
				}else{
					//it means its a pdf document which in our case we only need to download html page
					weblink.setDownloadStatus(WebLink.DownloadStatus.NOT_ELIGIBLE);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return weblink;
		}
	}

	public WebpageDownloaderTask(boolean downloadAll) {
		this.downloadAll = downloadAll;
	}

	public void run() {

		while (!Thread.currentThread().isInterrupted()) {

			// Get Weblinks
			List<WebLink> weblinks  = getAllWeblinks();

			// Download concurrently
			if(weblinks.size()>0){
				download(weblinks);
			}
			else{
				System.out.println("No new weblinks to download");
			}

			// Wait
			try {
				TimeUnit.SECONDS.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		downloadExecutor.shutdown();
	}

	private void download(List<WebLink> weblinks) {
	
		List<Downloader<WebLink>> tasks = getTasks(weblinks);
		List<Future<WebLink>> futures = new ArrayList<>();
		
		//we got the task and we submit to dowmlaoder executer and it returns future
		try {
			//we know invoke all will download all the weblinks 
			futures = downloadExecutor.invokeAll(tasks, TIME_FRAME, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (Future<WebLink> future : futures) {
			try {
				//if any of the downloader coundn't download any webpage it will be cancelled
				if (!future.isCancelled()) {
					WebLink webLink = future.get();//from weblink we can get a webpage once it's downloaded
					String webPage = webLink.getHtmlPage();
					if(webPage != null){// it means it has downloaded sucessfully
						IOUtil.write(webPage, webLink.getId());
						//we need to set the downloaded status to success as we downloaded successfully
						webLink.setDownloadStatus(WebLink.DownloadStatus.FAILED);
						System.out.println("Downloaded success " + webLink.getUrl());
					}else{
						System.out.println("Webpage not downloaded " + webLink.getUrl());
					}
				} else {
					System.out.println("\n\nTask is cancelled --> " + Thread.currentThread());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	private List<Downloader<WebLink>> getTasks(List<WebLink> weblinks) {
		List<Downloader<WebLink>> tasks = new ArrayList<>();
		for(WebLink weblink : weblinks ){
			tasks.add(new Downloader<WebLink>(weblink));
		}
		return tasks;
	}

	private List<WebLink> getAllWeblinks() {
		List<WebLink> weblinks = null;
		
		if(downloadAll){
			weblinks = dao.getAllWeblinks();
			downloadAll = false;
		}else {
			weblinks = dao.getWeblink(WebLink.DownloadStatus.NOT_ATTEMPTED);
		}
		return weblinks;
	}

}
