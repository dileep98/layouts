package com.terralogic;

import com.terralogic.entity.Role;
import com.terralogic.model.RoleEnum;
import com.terralogic.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
                RoleEnum.USER, "Default user role",
                RoleEnum.ADMIN, "Administrator role"
        );

        Arrays.stream(RoleEnum.values()).forEach(roleName -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(role -> log.info(role.toString()), () -> {
                Role roleToCreate = Role.builder()
                        .name(roleName)
                        .description(roleDescriptionMap.get(roleName))
                        .build();

                roleRepository.save(roleToCreate);
            });
        });
    }
}
