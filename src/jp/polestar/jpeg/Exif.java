package jp.polestar.jpeg;

import java.io.*;

import com.drew.imaging.ImageProcessingException;

public class Exif {
	
	private String fileName;
	private java.io.File jpegFile = null;
	private com.drew.metadata.Metadata metadata = null;
		
	public Exif( String fileName) throws IOException, ImageProcessingException{
		this.setFileName(fileName);
		
	}
		
	public String getFileName() {
		return fileName;
		
	}

	public void setFileName(String fileName)  throws IOException, ImageProcessingException
	{
		this.fileName = fileName;
		jpegFile = new java.io.File(this.getFileName());
		metadata = com.drew.imaging.ImageMetadataReader.readMetadata(jpegFile);
		
	}
	
	public void dump() throws 
		com.drew.imaging.ImageProcessingException, 
		IOException 
	{
	
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              // System.out.println(tag);
	              System.out.println(tag.getTagName() + "-----" + tag.getDescription());
	          
	              // System.out.println(tag.toString());
	             
	        }
	    }
		
	}

	public String getLatitude() throws UnsupportedEncodingException
	{
		String result = null;
		String ref = null;
		String value = null; 
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              // System.out.println(tag);
	              // System.out.println(tag.getTagName() + "-----" + tag.getDescription());
	              if (tag.getTagName().equals("GPS Latitude Ref"))  {
	            	  ref = tag.getDescription();
	              }
	              if (tag.getTagName().equals("GPS Latitude") ) {
	            	  value = tag.getDescription();
	              }
	        }
	    }

		if (ref == null && value == null) {
			return null; 
		}
		value = value.replace(String.valueOf(Character.toChars(39)), "");
		value = value.replace(String.valueOf(Character.toChars(46)), "");
		value = value.replace(String.valueOf(Character.toChars(32)), "");
		value = value.replace(String.valueOf(Character.toChars(34)), "");
		value = value.replace(String.valueOf(Character.toChars(63)), String.valueOf(Character.toChars(46)));
				
		byte[] a = value.getBytes("US-ASCII");
		byte[] b = new byte[a.length];
		int h = 0;
		for (int i=0; i<a.length; i++){
			if ( a[i] != 63 ){
				b[h] = a[i];
			}else{
				b[h] = 46;
			}
			h+=1;
		}

		value = new String(b);
		if ( !ref.equals("N")){
			result = "-" + value;
		}else{
			result = value;
		}
		return result;
	}
	
	public String getLongitude() throws UnsupportedEncodingException
	{
		String result = null;
		String ref = null;
		String value = null; 
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              // System.out.println(tag);
	              // System.out.println(tag.getTagName() + "-----" + tag.getDescription());
	              if (tag.getTagName().equals("GPS Longitude Ref"))  {
	            	  ref = tag.getDescription();
	              }
	              if (tag.getTagName().equals("GPS Longitude") ) {
	            	  value = tag.getDescription();
	              }
	        }
	    }

		if (ref == null && value == null) {
			return null; 
		}
		value = value.replace(String.valueOf(Character.toChars(39)), "");
		value = value.replace(String.valueOf(Character.toChars(46)), "");
		value = value.replace(String.valueOf(Character.toChars(32)), "");
		value = value.replace(String.valueOf(Character.toChars(34)), "");
		value = value.replace(String.valueOf(Character.toChars(63)), String.valueOf(Character.toChars(46)));
				
		byte[] a = value.getBytes("US-ASCII");
		byte[] b = new byte[a.length];
		int h = 0;
		for (int i=0; i<a.length; i++){
			if ( a[i] != 63 ){
				b[h] = a[i];
			}else{
				b[h] = 46;
			}
			h+=1;
		}

		value = new String(b);
		if ( !ref.equals("E")){
			result = "-" + value;
		}else{
			result = value;
		}
		return result;
	}

	public int getHeight() {
		String[] parseString = null;
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              if (tag.getTagName().equals("Image Height"))  {
	            	  parseString = tag.getDescription().split(" ");
	            	  break;
	              }
	        }
	    }
		
		if( parseString != null) {
			return new Integer(parseString[0]);
		} else {
			return 0;
		}
				
	}
	
	public int getWidth() {
		String[] parseString = null;
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              if (tag.getTagName().equals("Image Width"))  {
	            	  parseString = tag.getDescription().split(" ");
	            	  break;
	              }
	        }
	    }
		
		if( parseString != null) {
			return new Integer(parseString[0]);
		} else {
			return 0;
		}
				
	}
	
	public String getMake() {
		String parseString = null;
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              if (tag.getTagName().equals("Make"))  {
	            	  parseString = tag.getDescription();
	            	  break;
	              }
	        }
	    }
		
		return parseString;
				
	}
	
	public String getLensMake() {
		String parseString = null;
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              if (tag.getTagName().equals("Lens Make"))  {
	            	  parseString = tag.getDescription();
	            	  break;
	              }
	        }
	    }
		
		return parseString;
				
	}
	
	public String getModel() {
		String parseString = null;
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              if (tag.getTagName().equals("Model"))  {
	            	  parseString = tag.getDescription();
	            	  break;
	              }
	        }
	    }
		
		return parseString;
				
	}
	
	public String getLensModel() {
		String parseString = null;
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              if (tag.getTagName().equals("Lens Model"))  {
	            	  parseString = tag.getDescription();
	            	  break;
	              }
	        }
	    }
		
		return parseString;
				
	}
	
	public String getFNumber() {
		String parseString = null;
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              if (tag.getTagName().equals("F-Number"))  {
	            	  parseString = tag.getDescription();
	            	  break;
	              }
	        }
	    }
		
		return parseString;
				
	}
	
	public String getISOSpeed() {
		String parseString = null;
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              if (tag.getTagName().equals("ISO Speed Ratings"))  {
	            	  parseString = tag.getDescription();
	            	  break;
	              }
	        }
	    }
		
		return parseString;
				
	}
	
	public String getExifVersion() {
		String parseString = null;
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              if (tag.getTagName().equals("Exif Version")){
	            	  parseString = tag.getDescription();
	            	  break;
	              }
	        }
	    }
		
		return parseString;
				
	}
	
	public String getArtist() {
		String parseString = null;
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              if (tag.getTagName().equals("Artist")){
	            	  parseString = tag.getDescription();
	            	  break;
	              }
	        }
	    }
		
		return parseString;
				
	}
	
	public String getSoftware() {
		String parseString = null;
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              if (tag.getTagName().equals("Software")){
	            	  parseString = tag.getDescription();
	            	  break;
	              }
	        }
	    }
		
		return parseString;
				
	}
	
	public String getDateTime() {
		String parseString = null;
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              if (tag.getTagName().equals("Date/Time")){
	            	  parseString = tag.getDescription();
	            	  break;
	              }
	        }
	    }
		
		return parseString;
				
	}
		
	public String getShutterSpeedValue() {
		String parseString = null;
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              if (tag.getTagName().equals("Shutter Speed Value")){
	            	  parseString = tag.getDescription();
	            	  break;
	              }
	        }
	    }
		return parseString;
	}	
	
	/***
	 * 露出
	 * @return
	 */
	public String getExposureBiasValue() {
		String parseString = null;
		
		for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
			for (com.drew.metadata.Tag tag : directory.getTags()) {
	              if (tag.getTagName().equals("Exposure Bias Value")){
	            	  parseString = tag.getDescription();
	            	  break;
	              }
	        }
	    }
		return parseString;
	}	
	
}
