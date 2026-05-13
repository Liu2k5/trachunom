import { ViewConfig } from "@vaadin/hilla-file-router/types.js";
import { useState, useRef } from 'react';
import {Button, PasswordField, TextField} from "@vaadin/react-components";

export const config: ViewConfig = {
    route: 'login',
    menu: { exclude: true },
    title: 'Đăng nhập',
};

export default function LoginView() {
    const [error, setError] = useState('');
    const formRef = useRef<HTMLFormElement>(null);

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const form = e.currentTarget;
        const username = (form.elements.namedItem('username') as HTMLInputElement).value;
        const password = (form.elements.namedItem('password') as HTMLInputElement).value;
        setError('');

        const body = new URLSearchParams();
        body.append('username', username);
        body.append('password', password);

        try {
            const response = await fetch('/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-Requested-With': 'XMLHttpRequest',
                    'Accept': 'application/json',
                },
                body: body.toString(),
                credentials: 'same-origin',
            });

            if (response.status === 401) {
                try {
                    const data = await response.json();
                    if (data && data.error === 'incorrect_email_or_password') {
                        setError('Tên đăng nhập hoặc mật khẩu không đúng');
                    } else if (data && data.error === 'account_disabled') {
                        setError('Tài khoản đã bị vô hiệu hóa');
                    } else {
                        setError('Đăng nhập thất bại. Vui lòng thử lại');
                    }
                } catch (e) {
                    setError('Đăng nhập thất bại. Vui lòng thử lại');
                }
            } else if (response.ok || response.redirected || response.type === 'opaqueredirect') {
                // success
                window.location.href = '/';
            } else {
                setError('Đã xảy ra lỗi, vui lòng thử lại');
            }
        } catch (err) {
            setError('Có lỗi xảy ra, vui lòng thử lại');
        }
    };

    return (
        <div className="view-container" style={{
            display: 'flex',
            alignItems: 'center', justifyContent: 'center',
            height: '100%'
        }}>
            <div style={{
                width: '100%',
                maxWidth: '400px',
            }}>
                <h1 style={{ textAlign: 'center' }}>
                    Đăng nhập
                </h1>
                {error && (
                    <p style={{ color: 'var(--lumo-error-text-color)', textAlign: 'center' }}>{error}</p>
                )}
                <form
                    ref={formRef}
                    onSubmit={handleSubmit}
                    style={{ display: 'flex', flexDirection: 'column', gap: 'var(--lumo-space-m)' }}
                >
                    <TextField
                        label="Tên đăng nhập"
                        name="username"
                        required
                        onKeyDown={(e) => {
                            if (e.key === 'Enter') {
                                formRef.current?.requestSubmit();
                            }
                        }}
                    />
                    <PasswordField
                        label="Mật khẩu"
                        name="password"
                        required
                        onKeyDown={(e) => {
                            if (e.key === 'Enter') {
                                formRef.current?.requestSubmit();
                            }
                        }}
                    />
                    <Button theme="primary" onClick={() => formRef.current?.requestSubmit()}>Đăng nhập</Button>
                </form>
            </div>
        </div>
    );
}