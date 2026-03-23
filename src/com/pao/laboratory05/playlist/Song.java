
package com.pao.laboratory05.playlist;

public class Song implements Comparable<Song> {
  private String title;

  public String getTitle() {
    return title;
  }

  private String artist;
  private int durationSeconds;

  public int getDurationSeconds() {
    return durationSeconds;
  }

  public Song(String title, String artist, int durationSeconds) {
    this.title = title;
    this.artist = artist;
    this.durationSeconds = durationSeconds;
  }

  @Override
  public int compareTo(Song other) {
    return this.title.compareTo(other.title);
  }
}