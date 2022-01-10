package com.thackbarth.playwithjetpack.network

interface EntityMapper <Entity, DomainModel>{

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapFromDomain(domainModel: DomainModel): Entity

}