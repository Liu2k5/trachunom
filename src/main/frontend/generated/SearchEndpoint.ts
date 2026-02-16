import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type EntityX_1 from "./com/liu/trachunom/entity/EntityX.js";
import client_1 from "./connect-client.default.js";
async function findByQuery_1(query: string | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityX_1 | undefined> | undefined> { return client_1.call("SearchEndpoint", "findByQuery", { query }, init); }
export { findByQuery_1 as findByQuery };
