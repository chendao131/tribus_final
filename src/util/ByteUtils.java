package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * bytes --> number, number --> bytes
 * 
 * @author kevin
 */
public class ByteUtils {

	public static void main( String[] args ) {
		byte[] bs = ByteUtils.getBigBytesByLong( 2345678 );
		System.out.println( ByteUtils.readLongBig( bs ) );

		byte[] bs1 = ByteUtils.getLittleBytesByLong( 123456734454545456L );
		System.out.println( ByteUtils.readLongLittle( bs1 ) );
		
		int a = ByteUtils.readIntBig(bs);
		System.out.println(a);
	}
	
	 
	/**
	 * @param value
	 * @return
	 */
	public static byte[] getBigBytesByShort( short value ) {
		return ByteUtils.getBytesByShort( value, true );
	}
	
	/**
	 * @param value
	 * @param bigEndian
	 * @return
	 */
	private static byte[] getBytesByShort( short value, boolean bigEndian ) {
		ByteBuffer bb = ByteBuffer.allocate( 2 );
		if ( !bigEndian )
			bb.order( ByteOrder.LITTLE_ENDIAN );
		bb.putShort( value );
		byte[] array = bb.array( );
		return array;
	}
	
	public static byte[] getLittleShortByInt(short value){
		return ByteUtils.getBytesByShort(value,true);
	}
	
	/**
	 * @param value
	 * @return
	 */
	public static byte[] getLittleBytesByLong( long value ) {
		return ByteUtils.getBytesByLong( value, false );
	}

	/**
	 * @param value
	 * @return
	 */
	public static byte[] getBigBytesByLong( long value ) {
		return ByteUtils.getBytesByLong( value, true );
	}

	/**
	 * @param value
	 * @param bigEndian
	 * @return
	 */
	private static byte[] getBytesByLong( long value, boolean bigEndian ) {
		ByteBuffer bb = ByteBuffer.allocate( 8 );
		if ( !bigEndian )
			bb.order( ByteOrder.LITTLE_ENDIAN );
		bb.putLong( value );
		byte[] array = bb.array( );
		return array;
	}

	/**
	 * @param value
	 * @return
	 */
	public static byte[] getLittleBytesByInt( int value ) {
		return ByteUtils.getBytesByInt( value, false );
	}

	/**
	 * @param value
	 * @return
	 */
	public static byte[] getBigBytesByInt( int value ) {
		return ByteUtils.getBytesByInt( value, true );
	}

	/**
	 * @param value
	 * @param bigEndian
	 * @return
	 */
	private static byte[] getBytesByInt( int value, boolean bigEndian ) {
		ByteBuffer bb = ByteBuffer.allocate( 4 );
		if ( !bigEndian )
			bb.order( ByteOrder.LITTLE_ENDIAN );
		bb.putInt( value );
		byte[] array = bb.array( );
		return array;
	}

	/**
	 * @param bs
	 * @return
	 */
	public static long readLongBig( byte[] bs ) {
		return ByteUtils.readLongFromBytes( bs, true );
	}

	/**
	 * @param bs
	 * @return
	 */
	public static long readLongLittle( byte[] bs ) {
		return ByteUtils.readLongFromBytes( bs, false );
	}

	/**
	 * @param bs
	 * @param bigEndian
	 * @return
	 */
	private static long readLongFromBytes( byte[] bs, boolean bigEndian ) {
		if ( bs == null )
			return -1;
		long ret = 0;
		int pos = 0;
		for ( int i = 0; i < bs.length && i < 8; i++ ) {
			ret <<= 8;
			if ( bigEndian )
				pos = i;
			else
				pos = bs.length - 1 - i;
			ret |= bs[pos] & 0xFF;
		}
		return ret;
	}

	/**
	 * @param bs
	 * @return
	 */
	public static int readIntBig( byte[] bs ) {
		return ByteUtils.readIntFromBytes( bs, true );
	}

	/**
	 * @param bs
	 * @return
	 */
	public static int readIntLittle( byte[] bs ) {
		return ByteUtils.readIntFromBytes( bs, false );
	}

	/**
	 * @param bs
	 * @param bigEndian
	 * @return
	 */
	private static int readIntFromBytes( byte[] bs, boolean bigEndian ) {
		if ( bs == null )
			return -1;
		int ret = 0;
		int pos = 0;
		for ( int i = 0; i < bs.length && i < 4; i++ ) {
			ret <<= 8;
			if ( bigEndian )
				pos = i;
			else
				pos = bs.length - 1 - i;
			ret |= bs[pos] & 0xFF;
		}
		return ret;
	}
	
	public static byte[] getGZIPBinary( byte[] content ) {
		if ( content == null )
			return null;
		ByteArrayOutputStream tmpos = null;
		GZIPOutputStream gzos = null;
		try {
			tmpos = new ByteArrayOutputStream( );
			gzos = new GZIPOutputStream( tmpos );
			gzos.write( content, 0, content.length );
			gzos.finish( );
			byte[] gContent = tmpos.toByteArray( );
			return gContent;
		} catch ( Exception e ) {
			return null;
		} finally {
			try {
				gzos.close( );
			} catch ( Exception e ) {
			}
			try {
				tmpos.close( );
			} catch ( Exception e ) {
			}
		}
	}
	
	public static byte[] revertGZIP( byte[] data ) {
		ByteArrayInputStream bis = null;
		GZIPInputStream gzis = null;
		ByteArrayOutputStream bos = null;
		try {
			bis = new ByteArrayInputStream( data );
			gzis = new GZIPInputStream( bis );
			bos = new ByteArrayOutputStream( );
			byte[] b = new byte[1024];
			int nRead;
			while ( ( nRead = gzis.read( b, 0, 1024 ) ) > 0 ) {
				bos.write( b, 0, nRead );
			}
			return bos.toByteArray( );
		} catch ( Exception e ) {
			e.printStackTrace( );
		} finally {
			try {
				bos.close( );
			} catch ( Exception e ) {
			}
			try {
				gzis.close( );
			} catch ( Exception e ) {
			}
			try {
				bis.close( );
			} catch ( Exception e ) {
			}
		}
		return null;
	}	
}
