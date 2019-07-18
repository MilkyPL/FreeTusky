package com.thechiefmeat.freetusky.di

import com.google.gson.Gson
import com.thechiefmeat.freetusky.db.AccountManager
import com.thechiefmeat.freetusky.db.AppDatabase
import com.thechiefmeat.freetusky.network.MastodonApi
import com.thechiefmeat.freetusky.repository.TimelineRepository
import com.thechiefmeat.freetusky.repository.TimelineRepositoryImpl
import com.thechiefmeat.freetusky.util.HtmlConverter
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providesTimelineRepository(db: AppDatabase, mastodonApi: MastodonApi,
                                   accountManager: AccountManager, gson: Gson,
                                   htmlConverter: HtmlConverter): TimelineRepository {
        return TimelineRepositoryImpl(db.timelineDao(), mastodonApi, accountManager, gson,
                htmlConverter)
    }
}