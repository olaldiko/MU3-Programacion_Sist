import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileCopyPool {

	final int POOL_SIZE = 10;
	
	Path source;
	Path dest;
	
	ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
	
	public boolean setSourcePath(String path) {
		source = Paths.get(path);
		if(source.toFile().isDirectory()) {
			return true;
		} else {
			return false;
		}
	}
	public boolean setDestPath(String path) {
		dest = Paths.get(path);
		if(dest.toFile().isDirectory()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void copyFiles() {
		try {
			Files.walk(source).forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					pool.submit(new FileClonerRunnable(filePath, dest));
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.shutdown();
	}
}
