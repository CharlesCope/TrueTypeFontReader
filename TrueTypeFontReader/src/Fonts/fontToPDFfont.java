package Fonts;

import Fonts.table.NameTable;


/** Wanted a place to run code not tied to the GUI  */
public class fontToPDFfont {
	private static String OS = null;
	private static int intUnitsPerEM; 
	
	public static PDFFont ConvertFontFileToPDFFont(String strFile){
		if(strFile.isEmpty()){return null;}
		// First create the True Type Font Object
		ChcFont myChcFont = new ChcFont().create(strFile);
		// Then create the PDFFont Object to get data from True Type Font Object
		PDFFont myPDFFont = new PDFFont();
		myPDFFont.setUnitsPerEm(myChcFont.getHeadTable().getUnitsPerEm());
		intUnitsPerEM = myPDFFont.getUnitsPerEm();
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
		
		myPDFFont.setScriptFlag(myChcFont.getOS2Table().getIsScript());
		myPDFFont.setSerifFlag(myChcFont.getOS2Table().getIsSerif());		
		
		// TODO Need to find the data in file and set flags.
		//myPDFFont.setAllCapFlag(setFlag);
		//myPDFFont.setSmallCapFlag(setFlag);
		//myPDFFont.setForceBoldFlag(setFlag);

		
		Glyph MissingWidth = myChcFont.getGlyph(0);
		if (MissingWidth != null){myPDFFont.setMissingWidth(pdfScalingFormula(MissingWidth.advanceWidth,intUnitsPerEM));}
		else{myPDFFont.setMissingWidth(0);}
		
		int intVersion = myChcFont.getOS2Table().getVersion();
		
		if(intVersion >= 2){
			myPDFFont.setCapHeight(pdfScalingFormula(myChcFont.getOS2Table().getCapHeight(),intUnitsPerEM));
			myPDFFont.setXHeight(pdfScalingFormula(myChcFont.getOS2Table().getXHeight(),intUnitsPerEM));
		}
		/** NOTE: These are just rule-of-thumb values,in case the xHeight and CapHeight fields aren't available.*/
		else{
			System.out.println("Got Here");
			myPDFFont.setCapHeight((int) (.7 * intUnitsPerEM));
			myPDFFont.setXHeight((int) (.5 * intUnitsPerEM));
		}
		
		myPDFFont.setItalicAngle(myChcFont.getPostTable().getItalicAngle());
		myPDFFont.setAscent(pdfScalingFormula(myChcFont.getHheaTable().getAscender(),intUnitsPerEM));
		myPDFFont.setDescent(pdfScalingFormula(myChcFont.getHheaTable().getDescender(),intUnitsPerEM));
		myPDFFont.setLeading(pdfScalingFormula(myChcFont.getHheaTable().getLineGap(),intUnitsPerEM));
		myPDFFont.setStemV(myChcFont.getOS2Table().getWeightClass());
		myPDFFont.setMaxWidth(pdfScalingFormula(myChcFont.getHheaTable().getAdvanceWidthMax(),intUnitsPerEM));
		myPDFFont.setAvgWidth(pdfScalingFormula(myChcFont.getOS2Table().getAvgCharWidth(),intUnitsPerEM));
		// TODO Need to get the first character code defined in	the font�s Widths array.
		myPDFFont.setFirstChar(0);
		// TODO Need to getThe last character code defined in the font�s Widths array.
		myPDFFont.setLastChar(255);
		// If we make it here return the converted file object
		return myPDFFont;
		
	}
	   
    public static int pdfScalingFormula(int intAdvanceWidth, int intUnitsPerEm){
    	// Avoid divide by zero error.
    	if(intAdvanceWidth == 0 ){return 0;}
    	
    	return (intAdvanceWidth * 1000) / intUnitsPerEm;
    }

	public static String getOsName(){
		// The operating system of the host that my Java program is running 
		if(OS == null) { OS = System.getProperty("os.name"); }
	      return OS;
	}
	
	public static boolean isWindows(){return getOsName().startsWith("Windows");}
	
	public static boolean isMac(){return getOsName().startsWith("Mac");}
}
