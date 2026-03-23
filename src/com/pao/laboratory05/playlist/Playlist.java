package com.pao.laboratory05.playlist;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

import com.pao.laboratory01.exercise.Car;

public class Playlist {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  private Song[] songs = new Song[0];

  public Playlist() {
  }

  public Playlist(String name) {
    this.name = name;
  }

  public void addSong(Song song) {
    Song[] tmp = new Song[songs.length + 1];
    System.arraycopy(songs, 0, tmp, 0, songs.length);
    tmp[tmp.length - 1] = song;
    songs = tmp;
    System.out.println("Cantecul \"" + song.getTitle() + "\" a fost adaugat");
  }

  public void printSortedByTitle() {
    Song[] sorted = songs.clone();
    Arrays.sort(sorted);
    for (Song song : sorted) {
      System.out.println(song.getTitle());
    }
  }

  public void printSortedByDuration() {
    Song[] sorted = songs.clone();
    SongDurationComparator comparator = new SongDurationComparator();
    Arrays.sort(sorted, comparator);
    for (Song song : sorted) {
      System.out.println(song.getTitle());
    }
  }

  public int getTotalDuration() {
    int total = 0;
    for (Song song : songs) {
      total += song.getDurationSeconds();
    }
    return total;
  }
}
