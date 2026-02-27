import Flow from 'Frontend/generated/flow/Flow';
import { RouterConfigurationBuilder } from '@vaadin/hilla-file-router/runtime.js';
import fileRoutes from 'Frontend/generated/file-routes';

export const { router, routes } = new RouterConfigurationBuilder()
    .withFileRoutes(fileRoutes)
    .withFallback(Flow)
    .build();

// Set routesConfig cho Flow fallback
(window as any).Vaadin ??= {};
(window as any).Vaadin.routesConfig = routes;

export { router as default };
