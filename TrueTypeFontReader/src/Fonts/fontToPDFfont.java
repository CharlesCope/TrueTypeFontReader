package Fonts;

import Fonts.table.NameTable;


/** Wanted a place to run code not tied to the GUI  */
public class fontToPDFfont {
	private static String OS = null;
	
	public static PDFFont ConvertFontFileToPDFFont(String strFile){
		if(strFile.isEmpty()){return null;}
		// First create the True Type Font Object
		ChcFont myChcFont = new ChcFont().create(strFile);
		// Then create the PDFFont Object to get data from True Type Font Object
		PDFFont myPDFFont = new PDFFont();
	
		myPDFFont.setUnitsPerEm(myChcFont.getHeadTable().getUnitsPerEm());
		myPDFFont.setBoundingBoxLowerLeftx(myChcFont.getHeadTable().getXMin());
		myPDFFont.setBoundingBoxLowerLefty(myChcFont.getHeadTable().getYMin());
		myPDFFont.setBoundingBoxUpperRightx(myChcFont.getHeadTable().getXMax());
		myPDFFont.setBoundingBoxUpperRighty(myChcFont.getHeadTable().getYMax());
		
		String strBaseFontName = myChcFont.getNameTable().getRecord(NameTable.namePostscriptName);
		if(strBaseFontName.isEmpty() == false){myPDFFont.setFontBaseName(strBaseFontName);}
		else{
			// Do this in case the Postscript value is empty.
			if(isWindows() == true){}
			if(isMac() == true){}
		}

		// Need to find the data in file and set flags.
		myPDFFont.setFixedPitchFlag(myChcFont.getPostTable().getIsFixedPitch());
		//myPDFFont.setSerifFlag(setFlag);
		//myPDFFont.setSymbolicFlag(setFlag);
		//myPDFFont.setScriptFlag(setFlag);
		//myPDFFont.setNonsymbolicFlag(setFlag);
		//myPDFFont.setItalicFlag(myChcFont.getHeadTable().getMacStyle());
		//myPDFFont.setAllCapFlag(setFlag);
		//myPDFFont.setSmallCapFlag(setFlag);
		//myPDFFont.setForceBoldFlag(setFlag);
		
		
		
		// If we make it here return the converted file object
		return myPDFFont;
		
	}
	
	public static String getOsName(){
		// The operating system of the host that my Java program is running 
		if(OS == null) { OS = System.getProperty("os.name"); }
	      return OS;
	}
	
	public static boolean isWindows(){return getOsName().startsWith("Windows");}
	
	public static boolean isMac(){return getOsName().startsWith("Mac");}
}
