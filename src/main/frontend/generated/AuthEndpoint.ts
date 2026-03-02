import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type AuthInfo_1 from "./com/liu/trachunom/endpoint/guest/AuthEndpoint/AuthInfo.js";
import client_1 from "./connect-client.default.js";
async function getAuthInfo_1(init?: EndpointRequestInit_1): Promise<AuthInfo_1 | undefined> { return client_1.call("AuthEndpoint", "getAuthInfo", {}, init); }
export { getAuthInfo_1 as getAuthInfo };
