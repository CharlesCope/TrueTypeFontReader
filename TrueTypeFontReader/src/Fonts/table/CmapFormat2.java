package Fonts.table;

import java.io.IOException;
import java.io.RandomAccessFile;

public class CmapFormat2 extends CmapFormat {

    @SuppressWarnings("unused")
	private short[] subHeaderKeys = new short[256];
    @SuppressWarnings("unused")
	private int[] subHeaders1;
    @SuppressWarnings("unused")
	private int[] subHeaders2;
    @SuppressWarnings("unused")
	private short[] glyphIndexArray;

    protected CmapFormat2(RandomAccessFile raf) throws IOException {
        super(raf);
        format = 2;
    }

    public int getFirst() { return 0; }
    public int getLast()  { return 0; }
    
    public int mapCharCode(int charCode) {
        return 0;
    }
}
