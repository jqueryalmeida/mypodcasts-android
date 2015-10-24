package com.mypodcasts.episodes;

import com.mypodcasts.repositories.models.Episode;
import com.mypodcasts.support.ExternalPublicFileLookup;

import java.io.File;

import javax.inject.Inject;

import static android.os.Environment.DIRECTORY_PODCASTS;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class EpisodeFile {
  private final ExternalPublicFileLookup externalPublicFileLookup;

  @Inject
  public EpisodeFile(ExternalPublicFileLookup externalPublicFileLookup) {
    this.externalPublicFileLookup = externalPublicFileLookup;
  }

  public boolean exists(Episode episode) {
    return externalPublicFileLookup.exists(
        getExternalStoragePublicDirectory(getPodcastsDirectory()),
        episode.getAudioFilePath()
    );
  }

  public String getAudioFilePath(Episode episode) {
    if (exists(episode)) { return episode.getAudioFilePath(); }

    return episode.getAudioUrl();
  }

  public String getPodcastsDirectory() {
    return DIRECTORY_PODCASTS;
  }
}
