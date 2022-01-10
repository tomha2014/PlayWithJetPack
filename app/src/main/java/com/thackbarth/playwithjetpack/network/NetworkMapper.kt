package com.thackbarth.playwithjetpack.network

//import com.thackbarth.playwithjetpack.model.Photo
//import com.thackbarth.playwithjetpack.model.PhotoNetworkEntity
//
//
//class NetworkMapper
//constructor() : EntityMapper<PhotoNetworkEntity, Photo> {
//    override fun mapFromEntity(entity: PhotoNetworkEntity): Photo {
//        return Photo(
//            id = entity.id,
//            albumId = entity.albumId,
//            url = entity.url,
//            thumbnailUrl = entity.thumbnailUrl,
//            title = entity.title
//        )
//    }
//
//    override fun mapFromDomain(domainModel: Photo): PhotoNetworkEntity {
//        return PhotoNetworkEntity(
//            id = domainModel.id,
//            albumId = domainModel.albumId,
//            title = domainModel.title,
//            url = domainModel.url,
//            thumbnailUrl = domainModel.thumbnailUrl
//        )
//    }
//
//    fun mapFromEntityList(entities: List<PhotoNetworkEntity>):List<Photo> {
//        return entities.map{ mapFromEntity(it)}
//    }
//}