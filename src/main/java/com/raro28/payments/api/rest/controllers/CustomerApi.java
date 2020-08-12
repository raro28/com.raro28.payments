package com.raro28.payments.api.rest.controllers;

import com.raro28.payments.api.rest.models.CreatedResourceResponse;
import com.raro28.payments.api.rest.models.Customer;
import com.raro28.payments.api.rest.models.Source;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/customers")
public interface CustomerApi {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreatedResourceResponse> create(@RequestBody Customer customer);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> remove(@PathVariable String id);

    @PostMapping(value="/{id}/sources", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreatedResourceResponse> addSource(@PathVariable String id, @RequestBody Source source);
}