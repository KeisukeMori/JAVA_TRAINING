package ch20.ex20_09;

import java.io.File;
import java.io.IOException;

public class PrintFileInfo {

	private File file;
	
	public static void main(String [] args){
		File file = new File("test.dat");
		PrintFileInfo fileInfo = new PrintFileInfo(file);
		fileInfo.print();
	}
	
	public PrintFileInfo(File file){
		this.file = file;
	}
	
	public String getFileInfoString(){
		StringBuilder strBuilder = new StringBuilder();
		try {
			strBuilder.append("absolute path:" + file.getAbsolutePath() + "\n"); // 絶対パス
			strBuilder.append("canonical path:" + file.getCanonicalPath() + "\n"); // 正規パス
			strBuilder.append("name:" + file.getName() + "\n"); // 名前
			strBuilder.append("parent:" + file.getParent() + "\n"); // 親パス
			strBuilder.append("path:" + file.getPath() + "\n"); // パス名
			strBuilder.append("total space:" + file.getTotalSpace() + "\n"); // パーテーションサイズ
			strBuilder.append("usable space:" + file.getUsableSpace() + "\n"); // 利用可能バイト
			strBuilder.append("last modified:" + file.lastModified() + "\n"); // 最終更新日時
			strBuilder.append("length:" + file.length() + "\n"); // ファイル名長さ
			strBuilder.append("free space:" + file.getFreeSpace() + "\n"); // 未割当バイト
			strBuilder.append("executable:" + file.canExecute() + "\n"); //実行可能か
			strBuilder.append("readable:" + file.canRead() + "\n"); // 読み込み可能か
			strBuilder.append("writable:" + file.canWrite() + "\n"); // 変更可能か
			strBuilder.append("exists:" + file.exists() + "\n"); // 存在するか
			strBuilder.append("directory:" + file.isDirectory() + "\n"); // ディレクトリかどうか
			strBuilder.append("file:" + file.isFile() + "\n"); // ファイルかどうか
			strBuilder.append("hidden:" + file.isHidden() + "\n"); // 隠しかどうか
			strBuilder.append("Absolute:" + file.isAbsolute() + "\n"); // 隠しかどうか
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return strBuilder.toString();
	}
	
	public void print(){
		System.out.println(this.getFileInfoString());
	}
}
