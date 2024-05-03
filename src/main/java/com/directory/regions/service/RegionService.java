package com.directory.regions.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.directory.regions.model.Region;
import com.directory.regions.repository.RegionRepository;

@Service
public class RegionService {
    
    private final RegionRepository regionRepository;
    private final Logger log = LoggerFactory.getLogger(RegionService.class);

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Cacheable(value = "regions", key = "#size + '-' + #page")
    public List<Region> findAll(int size, int page) {
        final int offset = (page - 1) * size;
        log.info("regionRepository.findAll: size = {}, offset = {}", size, offset);
        return regionRepository.findAll(size, offset);
    }

    @Caching(
        evict = {
            @CacheEvict(value = "regions", allEntries = true),
            @CacheEvict(value = "regionsShortName", key = "#region.shortName")
        }
    )
    public void insert(Region region) {
        log.info("regionRepository.insert: region = {}", region);
        regionRepository.insert(region);
    }

    @Caching(
        evict = {
            @CacheEvict(value = "regions", allEntries = true),
            @CacheEvict(value = "regionsShortName", key = "#region.shortName"),
            @CacheEvict(value = "regionItem", key = "#region.id")
        }
    )
    public void deleteById(Region region) {
        log.info("regionRepository.deleteById: region = {}", region);
        regionRepository.deleteById(region);
    }

    @Caching(
        evict = {
            @CacheEvict(value = "regions", allEntries = true),
            @CacheEvict(value = "regionsShortName", allEntries = true),
            @CacheEvict(value = "regionItem", key = "#region.id")
        }
    )
    public Region update(Region region) {
        log.info("regionRepository.update: region = {}", region);
        regionRepository.update(region);
        return regionRepository.findByIdRegion(region);
    }

    @Cacheable(value = "regionItem", key = "#id")
    public Region findByIdRegion(UUID id) {
        Region region = new Region();
        region.setId(id);
        log.info("regionRepository.findByIdRegion: region = {}", region);
        return regionRepository.findByIdRegion(region);
    }

    @Cacheable(value = "regionsShortName", key = "#shortName")
    public List<Region> findByShortNameRegion(String shortName, int size, int page) {
        final int offset = (page - 1) * size;
        log.info("regionRepository.findByShortNameRegion: shortName = {}, size = {}, offset = {}", shortName, size, offset);
        return regionRepository.findByShortNameRegion(shortName, size, offset);
    }

}
