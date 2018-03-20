package net.esc20.txeis.sample.models;

public class FileModel {

	private String name = "";
	private boolean startProcess = true;
	private boolean filesWritten = false;
	private boolean nextFile = false;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStartProcess() {
		return startProcess;
	}

	public void setStartProcess(boolean startProcess) {
		this.startProcess = startProcess;
	}

	public boolean isFilesWritten() {
		return filesWritten;
	}

	public void setFilesWritten(boolean filesWritten) {
		this.filesWritten = filesWritten;
	}

	public boolean isNextFile() {
		return nextFile;
	}

	public void setNextFile(boolean nextFile) {
		this.nextFile = nextFile;
	}
	
}
