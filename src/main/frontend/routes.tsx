import Flow from 'Frontend/generated/flow/Flow';
import { RouterConfigurationBuilder } from '@vaadin/hilla-file-router/runtime.js';
import fileRoutes from 'Frontend/generated/file-routes';

export const { router, routes } = new RouterConfigurationBuilder()
    .withFileRoutes(fileRoutes)
    .protect('/login')
    .withFallback(Flow)
    .build();
