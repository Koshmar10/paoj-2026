
package com.pao.laboratory05.playlist;

public record Song(String title, String artist, int durationSeconds) implements Comparable<Song> {
	@Override
	public int compareTo(Song other) {
		return this.title.compareTo(other.title);
	}
  public String getTitle() {
    return title;
  }

  public String getArtist() {
    return artist;
  }

  public int getDurationSeconds() {
    return durationSeconds;
  }
}