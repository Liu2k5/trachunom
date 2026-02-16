import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type RadicalDto_1 from "./com/liu/trachunom/dto/RadicalDto.js";
import type Radical_1 from "./com/liu/trachunom/entity/Radical.js";
import client_1 from "./connect-client.default.js";
async function delete_1(id: string | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("RadicalEndpoint", "delete", { id }, init); }
async function list_1(init?: EndpointRequestInit_1): Promise<Array<RadicalDto_1 | undefined> | undefined> { return client_1.call("RadicalEndpoint", "list", {}, init); }
async function save_1(radicalDto: RadicalDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Radical_1 | undefined> { return client_1.call("RadicalEndpoint", "save", { radicalDto }, init); }
export { delete_1 as delete, list_1 as list, save_1 as save };
