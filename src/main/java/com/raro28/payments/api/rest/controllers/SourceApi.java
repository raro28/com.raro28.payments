package com.raro28.payments.api.rest.controllers;

import com.raro28.payments.api.models.Charge;
import com.raro28.payments.api.rest.models.CreatedResourceResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/sources")
public interface SourceApi {
    @DeleteMapping("/{id}")
    ResponseEntity<Void> remove(@PathVariable String id, @RequestParam String customerId);

    @PostMapping("/{id}/charges")
    ResponseEntity<CreatedResourceResponse> capture(@PathVariable String id, @RequestParam String customerId, @RequestBody Charge charge, @RequestHeader(required = false) String verifier);
}