package Fonts.table;

import java.io.IOException;
import java.io.RandomAccessFile;


public class CmapFormat6 extends CmapFormat {

    @SuppressWarnings("unused")
	private short format;
    @SuppressWarnings("unused")
	private short length;
    @SuppressWarnings("unused")
	private short version;
    @SuppressWarnings("unused")
	private short firstCode;
    @SuppressWarnings("unused")
	private short entryCount;
    @SuppressWarnings("unused")
	private short[] glyphIdArray;

    public short[] getGlyphIdArray() {
		return glyphIdArray;
	}

	public void setGlyphIdArray(short[] glyphIdArray) {
		this.glyphIdArray = glyphIdArray;
	}

	protected CmapFormat6(RandomAccessFile raf) throws IOException {
        super(raf);
        format = 6;
    }

    public int getFirst() { return 0; }
    public int getLast()  { return 0; }
    
    public int mapCharCode(int charCode) {
        return 0;
    }
}
