package util;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResourceReader {
	
	@SuppressWarnings("rawtypes")
	public static Image getImageFromJar(String s, Class classname) {
		// change from inputStream to byteArrayOutputStream
		Image image = null;
		InputStream inputstream = classname.getResourceAsStream(s);
		if (inputstream != null) {
			ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
			try {
				byte byteArray[] = new byte[1024];
				// read from inputStream to byteArray;
				for (int i = 0; (i = inputstream.read(byteArray)) >= 0;) {
					// write from byteArray to byteArrayOutputStream
					bytearrayoutputstream.write(byteArray, 0, i);
				}
				image = Toolkit.getDefaultToolkit().createImage(
						bytearrayoutputstream.toByteArray());
			} catch (IOException ioexception) {
				ioexception.printStackTrace();
			}
		}
		return image;
	}

}
