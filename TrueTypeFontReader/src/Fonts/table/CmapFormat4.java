package Fonts.table;

import java.io.IOException;
import java.io.RandomAccessFile;

import Fonts.ChcFont;

public class CmapFormat4 extends CmapFormat {

    public  int language;
    private int segCountX2;
    private int searchRange;
    private int entrySelector;
    private int rangeShift;
    private int segCount;
    private int first, last, intCount;
    private int[] endCode;
    private int[] startCode;
    private int[] idDelta;
    private int[] idRangeOffset;
    private int[] glyphIdArray;
  

    protected CmapFormat4(RandomAccessFile raf) throws IOException {
    	super(raf);
    	format = 4;
        segCountX2 = raf.readUnsignedShort();
        segCount = segCountX2 / 2;
        endCode = new int[segCount];
        startCode = new int[segCount];
        idDelta = new int[segCount];
        idRangeOffset = new int[segCount];
        searchRange = raf.readUnsignedShort();
        entrySelector = raf.readUnsignedShort();
        rangeShift = raf.readUnsignedShort();
        last = -1;
       System.out.println("This is a test " + ChcFont.intGlyphCount);
        // Ending character code for each segment, last = 0xFFFF.
        for (int i = 0; i < segCount; i++) {
            endCode[i] = raf.readUnsignedShort();
            if (endCode[i] > last) last = endCode[i];
        }
        // reserve Pad This value should be zero
        raf.readUnsignedShort(); 
       
        // Starting character code for each segment
        for (int i = 0; i < segCount; i++) {
            startCode[i] = raf.readUnsignedShort();
            if ((i==0 ) || (startCode[i] < first)) first = startCode[i];
        }
        // Delta for all character codes in segment
        for (int i = 0; i < segCount; i++) {idDelta[i] = raf.readUnsignedShort();}
        // Offset in bytes to glyph indexArray, or 0
        for (int i = 0; i < segCount; i++) {idRangeOffset[i] = raf.readUnsignedShort();}

        
        // Whatever remains of this header belongs in glyphIdArray
        intCount = (length - 16 - (segCount*8)) / 2;
        glyphIdArray = new int[intCount];
        for (int i = 0; i < intCount; i++) {glyphIdArray[i] = raf.readUnsignedShort();}
    }

    public int getFirst() { return first; }
    public int getLast()  { return last; }
    public int getGlyphCount(){return intCount;}
    public int[] getGlyphIdArray() {return glyphIdArray;}
    
    public int mapCharCode(int charCode) {
        try {
            /*
              Quoting :
              http://developer.apple.com/fonts/TTRefMan/RM06/Chap6cmap.html#Surrogates
               
              The original architecture of the Unicode Standard
              allowed for all encoded characters to be represented
              using sixteen bit code points. This allowed for up to
              65,354 characters to be encoded. (Unicode code points
              U+FFFE and U+FFFF are reserved and unavailable to
              represent characters. For more details, see The Unicode
              Standard.)
               
              My comment : Isn't there a typo here ? Shouldn't we
              rather read 65,534 ?
              */
            if ((charCode < 0) || (charCode >= 0xFFFE))
                return 0;

            for (int i = 0; i < segCount; i++) {
                if (endCode[i] >= charCode) {
                    if (startCode[i] <= charCode) {
                        if (idRangeOffset[i] > 0) {
                            return glyphIdArray[idRangeOffset[i]/2 + 
                                                (charCode - startCode[i]) -
                                                (segCount - i)];
                        } else {
                            return (idDelta[i] + charCode) % 65536;
                        }
                    } else {
                        break;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("error: Array out of bounds - " + e.getMessage());
        }
        return 0;
    }

    public String toString() {
        return new StringBuffer()
        .append(super.toString())
        .append(", segCountX2: ")
        .append(segCountX2)
        .append(", searchRange: ")
        .append(searchRange)
        .append(", entrySelector: ")
        .append(entrySelector)
        .append(", rangeShift: ")
        .append(rangeShift)
        .append(", endCode: ")
        .append(endCode)
        .append(", startCode: ")
        .append(endCode)
        .append(", idDelta: ")
        .append(idDelta)
        .append(", idRangeOffset: ")
        .append(idRangeOffset).toString();
    }
}
