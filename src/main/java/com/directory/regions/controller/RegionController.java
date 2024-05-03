package com.directory.regions.controller;

import org.springframework.web.bind.annotation.RestController;

import com.directory.regions.model.InRegionDTO;
import com.directory.regions.model.Region;
import com.directory.regions.service.RegionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/")
@Tag(name = "Region API")
public class RegionController {
    
    private final RegionService regionService;
    private final Logger log = LoggerFactory.getLogger(RegionController.class);

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @Operation(summary = "Get list of all regions from db")
    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                      @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
        if (page <= 0 || size <= 0) {
            return ResponseEntity.badRequest().build();
        }
        log.info("regionService.findAll: size = {}, page = {}", size, page);
        List<Region> listAllRegions = regionService.findAll(size, page);
        return ResponseEntity.ok().body(listAllRegions);
    }

    @Operation(summary = "Save new region to \'regions\' table", description = "To save need only name of new region and his short name")
    @PostMapping
    public ResponseEntity<?> createRegion(@RequestBody InRegionDTO inRegionDTO) {
        if (inRegionDTO.getName() == null || inRegionDTO.getName().isEmpty()
                || inRegionDTO.getShortName() == null || inRegionDTO.getShortName().isEmpty() 
                || inRegionDTO.getName().length() > 500 || inRegionDTO.getShortName().length() > 500) {
            return ResponseEntity.badRequest().build();
        }
        Region region = new Region(inRegionDTO.getName(), inRegionDTO.getShortName());
        log.info("regionService.insert: region = {}", region);
        regionService.insert(region);
        return ResponseEntity.status(HttpStatus.CREATED).body(region);
    }

    @Operation(summary = "Delete region from db by his UUID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable UUID id) {
        log.info("regionService.findByIdRegion: id = {}", id);
        Region region = regionService.findByIdRegion(id);
        if (region == null) {
            return ResponseEntity.notFound().build();
        }
        log.info("regionService.deleteById: region = {}", region);
        regionService.deleteById(region);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update fields of entity region")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRegion(@PathVariable UUID id, @RequestBody InRegionDTO inRegionDTO) {
        if (inRegionDTO.getName() == null || inRegionDTO.getName().isEmpty()
                || inRegionDTO.getShortName() == null || inRegionDTO.getShortName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        log.info("regionService.findByIdRegion: id = {}", id);
        Region region = regionService.findByIdRegion(id);
        if (region == null) {
            return ResponseEntity.notFound().build();
        }
        
        region.setName(inRegionDTO.getName());
        region.setShortName(inRegionDTO.getShortName());

        log.info("regionService.update: region = {}", region);
        Region newRegion = regionService.update(region);
        return ResponseEntity.ok(newRegion);
    }

    @Operation(summary = "Get data of region by his UUID")
    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable UUID id) {
        
        log.info("regionService.findByIdRegion: id = {}", id);
        Region region = regionService.findByIdRegion(id);
        if (region == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(region);
    }
    
    @Operation(summary = "Search all regions by common short name")
    @GetMapping("/short/{shortName}")
    public ResponseEntity<List<Region>> getRegionByShortName(
                                    @PathVariable String shortName,
                                    @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        
        if (page <= 0 || size <= 0) {
            return ResponseEntity.badRequest().build();
        }
        
        log.info("regionService.findByShortNameRegion: shortName = {}, size = {}, page = {}", shortName, size, page);
        List<Region> listRegion = regionService.findByShortNameRegion(shortName, size, page);
        return ResponseEntity.ok().body(listRegion);
    }
}
