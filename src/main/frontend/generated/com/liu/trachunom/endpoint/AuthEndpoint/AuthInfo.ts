interface AuthInfo {
    loggedIn: boolean;
    username?: string;
    roles?: Array<string | undefined>;
}
export default AuthInfo;
