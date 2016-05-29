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


		myPDFFont.setFixedPitchFlag(myChcFont.getPostTable().getIsFixedPitch());
		
		/** Only Roman Encoding or Windows uni-code are allowed for a non symbolic font 
		 *  For symbolic font, no encoding entry is allowed and only one encoding entry is expected into the FontFile CMap
		 *  Any font whose character set is not a subset of the Adobe standard character set is considered to be symbolic.
		 *  If the Symbolic flag should be set then the Nonsymbolic flag must be cleared .
		 * */
		if(isWindows() == true){
			if(myChcFont.getCmapTable().getCmapFormat(NameTable.platformMicrosoft, NameTable.encodingUGL)!= null){
				myPDFFont.setNonsymbolicFlag(true);
				myPDFFont.setSymbolicFlag(false);}
			else {
				myPDFFont.setNonsymbolicFlag(false);
				myPDFFont.setSymbolicFlag(true);
			}
		}
		if(isMac() == true){
			if(myChcFont.getCmapTable().getCmapFormat(NameTable.platformMacintosh, NameTable.encodingRoman)!= null){
				myPDFFont.setNonsymbolicFlag(true);
				myPDFFont.setSymbolicFlag(false);}
			else {
				myPDFFont.setNonsymbolicFlag(false);
				myPDFFont.setSymbolicFlag(true);}
		}
		
		// Apple/Mac platform calls this the Style table and Microsoft Calls it SubFamily.
		String strStyle = myChcFont.getNameTable().getRecord(NameTable.nameFontSubfamilyName);
		if (strStyle.toUpperCase().contains("ITALI")== true){myPDFFont.setItalicFlag(true);}
		else{myPDFFont.setItalicFlag(false);}
		
		myPDFFont.setSerifFlag(myChcFont.getOS2Table().getIsSerif());		
		
		
		// Need to find the data in file and set flags.
		//myPDFFont.setScriptFlag(setFlag);
		
		//
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
