import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type StructureComponentDto_1 from "./com/liu/trachunom/dto/StructureComponentDto.js";
import type StructureComponentId_1 from "./com/liu/trachunom/entity/structure/StructureComponentId.js";
import client_1 from "./connect-client.default.js";
async function delete_1(id: StructureComponentId_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("StructureComponentEndpoint", "delete", { id }, init); }
async function save_1(structureComponentDto: StructureComponentDto_1 | undefined, init?: EndpointRequestInit_1): Promise<StructureComponentDto_1 | undefined> { return client_1.call("StructureComponentEndpoint", "save", { structureComponentDto }, init); }
export { delete_1 as delete, save_1 as save };
