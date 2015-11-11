import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileClonerRunnable implements Runnable {

	Path source;
	Path dest;
	
	public FileClonerRunnable(Path source, Path dest) {
		this.source = source;
		this.dest = dest;
	}
	
	@Override
	public void run() {
		try {
			dest = Paths.get(dest.toString(), source.getFileName().toString());
			Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File "+source.toString()+" ******* copied to *********"+dest.toString());
		} catch (IOException e) {}
	}
}
