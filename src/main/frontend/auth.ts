import { configureAuth } from '@vaadin/hilla-react-auth';
import * as AuthEndpoint from 'Frontend/generated/AuthEndpoint';

const auth = configureAuth(async () => {
    const info = await AuthEndpoint.getAuthInfo();
    if (info?.loggedIn) {
        return { name: info.username, roles: info.roles };
    }
    return undefined;
});

export const { AuthProvider, useAuth } = auth;

