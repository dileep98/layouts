package com.terralogic.controller;

import com.terralogic.entity.GroupLayout;
import com.terralogic.entity.Layout;
import com.terralogic.entity.UserLayout;
import com.terralogic.service.LayoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/layouts")
@RequiredArgsConstructor
public class LayoutController {

    private final LayoutService layoutService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Layout>> getAllLayouts() {
        return ResponseEntity.ok(layoutService.getAllLayouts());
    }

    @PostMapping("/assign/user")
    public ResponseEntity<String> assignLayoutToUser(@RequestParam Long layoutId) {
        layoutService.assignLayoutToUser(layoutId);
        return ResponseEntity.ok("Layout assigned to user successfully");
    }

    @PostMapping("/assign/group")
    public ResponseEntity<String> assignLayoutToGroup(@RequestParam Long groupId, @RequestParam Long layoutId) {
        layoutService.assignLayoutToGroup(groupId, layoutId);
        return ResponseEntity.ok("Layout assigned to group successfully");
    }

    @PutMapping("/update/user")
    public ResponseEntity<String> updateUserLayout(@RequestParam Long layoutId) {
        layoutService.updateUserLayout(layoutId);
        return ResponseEntity.ok("User layout updated successfully");
    }

    @GetMapping("/user")
    public ResponseEntity<UserLayout> getUserLayout() {
        return layoutService.getLayoutByUserId()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<GroupLayout> getGroupLayout(@PathVariable Long groupId) {
        return layoutService.getLayoutByGroupId(groupId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

