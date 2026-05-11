import { createRoute as createRoute_1 } from "@vaadin/hilla-file-router/runtime.js";
import type { AgnosticRoute as AgnosticRoute_1 } from "@vaadin/hilla-file-router/types.js";
import { lazy as lazy_1 } from "react";
const routes: readonly AgnosticRoute_1[] = [
    createRoute_1("DictionaryManagementView", lazy_1(() => import("../views/DictionaryManagementView.js")), { "route": "/admin/dictionary-management", "menu": { "order": 2, "icon": "la la-book" }, "title": "Qu\u1EA3n L\u00FD T\u1EEB \u0110i\u1EC3n", "loginRequired": true, "flowLayout": false }),
    createRoute_1("EntityDetailView", lazy_1(() => import("../views/EntityDetailView.js")), { "route": "entity/:id", "title": "Chi Ti\u1EBFt Th\u1EF1c Th\u1EC3", "flowLayout": false }),
    createRoute_1("IndexView", lazy_1(() => import("../views/IndexView.js")), { "route": "", "menu": { "order": 0, "icon": "la la-home" }, "title": "Tra Ch\u1EEF N\u00F4m", "flowLayout": false }),
    createRoute_1("LoginView", lazy_1(() => import("../views/LoginView.js")), { "route": "login", "menu": { "exclude": true }, "title": "\u0110\u0103ng nh\u1EADp", "flowLayout": false }),
    createRoute_1("SearchView", lazy_1(() => import("../views/SearchView.js")), { "route": "search", "menu": { "order": 1, "icon": "la la-search" }, "title": "T\u00ECm Ch\u1EEF", "flowLayout": false })
];
export default routes;
