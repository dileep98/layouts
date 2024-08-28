package com.terralogic.service;

import com.terralogic.entity.GroupLayout;
import com.terralogic.entity.Layout;
import com.terralogic.entity.UserLayout;
import com.terralogic.repository.GroupLayoutRepository;
import com.terralogic.repository.LayoutRepository;
import com.terralogic.repository.UserLayoutRepository;
import com.terralogic.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LayoutService {

    private final LayoutRepository layoutRepository;

    private final UserLayoutRepository userLayoutRepository;

    private final GroupLayoutRepository groupLayoutRepository;

    public List<Layout> getAllLayouts() {
        return layoutRepository.findAll();
    }

    public void assignLayoutToUser(Long layoutId) {
        Layout layout = layoutRepository.findById(layoutId)
                .orElseThrow(() -> new RuntimeException("Layout not found"));
        UserLayout userLayout = new UserLayout();
        userLayout.setUser(JwtUtil.getCurrentLoggedInUser().orElseThrow());
        userLayout.setLayout(layout);
        userLayoutRepository.save(userLayout);
    }

    public void assignLayoutToGroup(Long groupId, Long layoutId) {
        Layout layout = layoutRepository.findById(layoutId)
                .orElseThrow(() -> new RuntimeException("Layout not found"));
        GroupLayout groupLayout = new GroupLayout();
        groupLayout.setGroupId(groupId);
        groupLayout.setLayout(layout);
        groupLayoutRepository.save(groupLayout);
    }

    public Optional<UserLayout> getLayoutByUserId() {
        Long userId = JwtUtil.getCurrentLoggedInUser().orElseThrow().getId();
        return userLayoutRepository.findByUserId(userId);
    }

    public Optional<GroupLayout> getLayoutByGroupId(Long groupId) {
        return groupLayoutRepository.findByGroupId(groupId);
    }

    public void updateUserLayout(Long layoutId) {
        UserLayout userLayout = userLayoutRepository.findByUserId(JwtUtil.getCurrentLoggedInUser().orElseThrow().getId())
                .orElseThrow(() -> new RuntimeException("User Layout not found"));
        Layout layout = layoutRepository.findById(layoutId)
                .orElseThrow(() -> new RuntimeException("Layout not found"));
        userLayout.setLayout(layout);
        userLayoutRepository.save(userLayout);
    }
}