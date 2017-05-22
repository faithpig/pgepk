package com.whucs.pgepk.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegCut {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "<img src=\"/pgepk/file/picture/20140605205321_46.jpg\" width=\"123\" height=\"125\" alt=\"\" />哈哈哈啊哈我饿大花木荷是";
		Pattern p = Pattern.compile("<img[^<>]*?\\ssrc=['\"]?(.*?)['\"].*?>");
		Matcher m = p.matcher(s);
		List<String> result=new ArrayList<String>();
		while(m.find()){
			result.add(m.group());
		}
		for(String s1:result){
			StringBuffer sb=new StringBuffer();
			char s2[]=s1.trim().toCharArray();
			for(int i=0;i<s2.length;i++){
				if(s2[i]=='s'&&s2[i+1]=='r'&&s2[i+2]=='c'&&s2[i+3]=='='&&s2[i+4]=='"'){
					for(i=i+5;i<s2.length;i++){
						if(s2[i]!='"'){
							sb.append(s2[i]);
						}else{
							break;
						}
					}
				}
			}
			System.out.println(sb);
		}

	}

}
