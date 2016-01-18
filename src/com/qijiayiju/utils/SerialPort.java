package com.qijiayiju.utils;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.dwin.navy.serialportapi.SerailPortOpt;

import android.content.Context;
import android.util.Log;

/**
 * �Զ��崮�ڶ���
 * 
 * @author F
 * 
 */
public class SerialPort {
	Context context;

	/**
	 * �Զ��崮�ڶ���
	 */
	private static SerialPort serialPort;

	/**
	 * ����JNI�Ĵ���
	 */
	private SerailPortOpt serialportopt;

	/**
	 * ��ȡ�����߳�
	 */

	/**
	 * ���ڽ��յ�����
	 */
	private InputStream mInputStream;

	/**
	 * �����жϴ����Ƿ��Ѵ�
	 */
	public boolean isOpen = false;

	/*
	 * ���յ�������
	 */
	String data;

	/**
	 * ʵ�������򿪴��ڶ���
	 * 
	 * @param devNum
	 *            ���ں� S0��S1��S2��S3,S4
	 * @param dataBits
	 *            ����λ
	 * @param speed
	 *            ������
	 * @param stopBits
	 *            ֹͣλ
	 * @param parity
	 *            У��λ
	 */
	public SerialPort(String devNum, int speed, int dataBits, int stopBits,
			int parity) {
		serialportopt = new SerailPortOpt();
		openSerial(devNum, speed, dataBits, stopBits, parity);
	}

	/**
	 * �򿪴���ʱ�������������ָ����ĳ�����ڣ���������Ӧ�Ĳ���
	 * 
	 * @param devNum
	 *            ���ں� COM0��COM1��COM2��COM3,COM4
	 * @param dataBits
	 *            ����λ
	 * @param speed
	 *            ������
	 * @param stopBits
	 *            ֹͣλ
	 * @param parity
	 *            У��λ
	 * @return
	 */
	private boolean openSerial(String devNum, int speed, int dataBits,
			int stopBits, int parity) {
		serialportopt.mDevNum = devNum;
		serialportopt.mDataBits = dataBits;
		serialportopt.mSpeed = speed;
		serialportopt.mStopBits = stopBits;
		serialportopt.mParity = parity;

		// �򿪴���
		FileDescriptor fd = serialportopt.openDev(serialportopt.mDevNum);
		if (fd == null) {
			return false;// ���ڴ�ʧ��
		} else {
			// ���ô��ڲ���
			serialportopt.setSpeed(fd, speed);
			serialportopt.setParity(fd, dataBits, stopBits, parity);
			mInputStream = serialportopt.getInputStream();
			isOpen = true;
			return true;
		}
	}

	/**
	 * �رմ���
	 */
	public void closeSerial() {
		if (serialportopt.mFd != null) {
			serialportopt.closeDev(serialportopt.mFd);
			isOpen = false;
		}
	}

	/**
	 * ��������
	 * 
	 * @param data
	 *            ��������
	 */
	public void sendData(String data, String type) {
		try {
			serialportopt.writeBytes(type.equals("HEX") ? HexString2Bytes(data
					.length() % 2 == 1 ? data += "0" : data.replace(" ", ""))
					: HexString2Bytes(toHexString(data)));
		} catch (Exception e) {

		}
	}

	/**
	 * ��������
	 * 
	 * @param �շ���������
	 * @return ���յ����ַ���
	 */
	public String receiveData(String type) {

		int size;
		if (mInputStream == null) {
			return null;
		}
		byte[] buf = new byte[1024];
		size = serialportopt.readBytes(buf);
		if (size > 0) {
			try {
				data = type.equals("HEX") ? bytesToHexString(buf, size)
						: new String(buf, 0, size, "gb2312").trim().toString();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return data;
		} else {
			return null;
		}
	}

	/**
	 * ת���ַ���Ϊʮ�����Ʊ���
	 * 
	 * @param s
	 * @return
	 */
	private String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	/**
	 * ��ָ���ַ���src����ÿ�����ַ��ָ�ת��Ϊ16������ʽ �磺"2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF,
	 * 0xD9}
	 * 
	 * @param src
	 *            String
	 * @return byte[]
	 */
	private static byte[] HexString2Bytes(String src) {
		byte[] ret = new byte[src.length() / 2];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < tmp.length / 2; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}

	/**
	 * ��Hex����ת��ΪHex�ַ���
	 * 
	 * @param src
	 * @param size
	 * @return
	 */
	public static String bytesToHexString(byte[] src, int size) {
		String ret = "";
		if (src == null || size <= 0) {
			return null;
		}
		for (int i = 0; i < size; i++) {
			String hex = Integer.toHexString(src[i] & 0xFF);
			if (hex.length() < 2) {
				hex = "0" + hex;
			}
			hex += " ";
			ret += hex;
		}
		return ret.toUpperCase();
	}

	/**
	 * ������ASCII�ַ��ϳ�һ���ֽڣ� �磺"EF"--> 0xEF
	 * 
	 * @param src0
	 *            byte
	 * @param src1
	 *            byte
	 * @return byte
	 */
	private static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

}
