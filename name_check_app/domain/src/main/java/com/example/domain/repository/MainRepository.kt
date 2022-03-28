package com.example.domain.repository

import com.example.domain.model.DomainLoveResponse
import com.example.domain.utils.RemoteErrorEmitter

interface MainRepository {
    suspend fun checkLoveCalculator(
        remoteErrorEmitter: RemoteErrorEmitter,
        host: String,
        key: String,
        // fname = 남자 이름
        mName: String,
        // sname = 여자 이름
        wName: String
    ): DomainLoveResponse?
}