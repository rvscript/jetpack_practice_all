package com.example.jetpackpracticeall.domian.mapper

import com.example.jetpackpracticeall.db.models.domains.*
import com.example.jetpackpracticeall.domian.models.dto.PhotoDto
class NasaDtoMapper: DtoMapper<PhotoDto, PhotoDomain> {
    override fun mapToDomainModel(model: PhotoDto): PhotoDomain {
        return PhotoDomain(
            camera = model.camera?.id,
            earth_date = model.earth_date,
            id = model.id,
            img_src = model.img_src,
            rover = model.rover?.id,
            sol = model.sol
        )
    }

//     fun mapFromDomainModel(domainModel: PhotoDomain): PhotoDto {
//        return PhotoDto(
//            camera = camera,
//            earth_date = domainModel.earth_date,
//            id = domainModel.id,
//            img_src = domainModel.img_src,
//            rover = rover,
//            sol = domainModel.sol
//        )
//    }

    fun toDomainList(initial: List<PhotoDto>): List<PhotoDomain> {
        return initial.map { mapToDomainModel(it) }
    }

//    fun fromDomainList(initial: List<PhotoDomain>): List<PhotoDto> {
//        return initial.map { mapFromDomainModel(it) }
//    }
}