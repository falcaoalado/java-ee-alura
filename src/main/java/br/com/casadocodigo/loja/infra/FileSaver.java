package br.com.casadocodigo.loja.infra;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
public class FileSaver {

	@Resource(lookup = "java:global/serverPath")
	private String serverPath;
	
	public String write(Part arquivo, String path) {
		try {
			arquivo.write(serverPath + path + "/" + arquivo.getSubmittedFileName());
			
			return path + "/" + arquivo.getSubmittedFileName();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void transfer(Path arquivo, OutputStream outputStream) {
		try {
			FileInputStream inputStream = new FileInputStream(arquivo.toFile());
			try (
					ReadableByteChannel inputChannel = Channels.newChannel(inputStream);
					WritableByteChannel outputChannel = Channels.newChannel(outputStream);
				) {
				
				ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 10);
				
				while(inputChannel.read(buffer) != -1) {
					buffer.flip();
					outputChannel.write(buffer);
					buffer.clear();
				}
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
