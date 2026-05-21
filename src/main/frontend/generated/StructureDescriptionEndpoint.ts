import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type StructureDescriptionDto_1 from "./com/liu/trachunom/dto/StructureDescriptionDto.js";
import client_1 from "./connect-client.default.js";
async function delete_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("StructureDescriptionEndpoint", "delete", { id }, init); }
async function save_1(structureDescriptionDto: StructureDescriptionDto_1 | undefined, init?: EndpointRequestInit_1): Promise<StructureDescriptionDto_1 | undefined> { return client_1.call("StructureDescriptionEndpoint", "save", { structureDescriptionDto }, init); }
export { delete_1 as delete, save_1 as save };
