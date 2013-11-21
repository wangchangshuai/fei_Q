package common;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * 2011年10月
 * 
 * 山东科技大学信息学院  版权所有
 * 
 * 联系邮箱：415939252@qq.com
 * 
 * Copyright © 1999-2012, sdust, All Rights Reserved
 * 
 * @author 王昌帅，司吉峰，王松松 （计算机2009-5、6班）
 *
 */
public class playAudio extends Thread
{
	private String				file_path;				// 播放时改路径
	private AudioInputStream	ais;
	private SourceDataLine		line;
	private AudioFormat			baseFormat;
	private static final int	BUFFER_SIZE	= 4000 * 4;
	int							flag		= 1;

	public playAudio(int flag)
	{
		this.flag = flag;
		start();
	}

	private SourceDataLine getLine(AudioFormat audioFormat)
	{
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		try
		{
			res = (SourceDataLine) AudioSystem.getLine(info);
			res.open(audioFormat);
		}
		catch (Exception e)
		{}
		return res;
	}

	public void run()
	{
		try
		{
			if (flag == 1)
			{
				file_path = "audio/system.wav";
			}
			else if (flag == 2)
			{
				file_path = "audio/Audio.wav";
			}
			else
			{
				file_path = "audio/Global.wav";
			}
			ais = AudioSystem.getAudioInputStream(new File(file_path));
			baseFormat = ais.getFormat();
			line = getLine(baseFormat);
			line.start();
			int inBytes = 0;
			byte[] audioData = new byte[BUFFER_SIZE];
			while (inBytes != -1)
			{
				inBytes = ais.read(audioData, 0, BUFFER_SIZE);
				if (inBytes >= 0)
				{
					int outBytes = line.write(audioData, 0, inBytes);
				}
			}
		}
		catch (UnsupportedAudioFileException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
