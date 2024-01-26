package com.example.jetpackpracticeall.domian.mapper

interface DtoMapper <T, DomainModel> {
    fun mapToDomainModel(model: T): DomainModel
//    fun mapFromDomainModel(domainModel: DomainModel): T
}