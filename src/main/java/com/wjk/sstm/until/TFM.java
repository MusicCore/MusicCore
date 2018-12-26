package com.wjk.sstm.until;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.MD5Digest;

public class TFM {
    /**
     * md5 加密
     * @param str
     * @return
     */
    public static String md5(String str){
        MD5Digest digest = new MD5Digest();
        byte[] bytes = str.getBytes();
        byte[] buf = new byte[digest.getDigestSize()];
        digest.update(bytes, 0, bytes.length);
        digest.doFinal(buf, 0);
        return Hex.encodeHexString(buf);
    }
}
