import { ViewConfig } from "@vaadin/hilla-file-router/types.js";
import { useState, useEffect } from 'react';

export const config: ViewConfig = {
    route: 'login',
    menu: { exclude: true },
    title: 'Đăng nhập',
};

export default function LoginView() {
    const [error, setError] = useState('');

    // // Show message from query parameters (set by SecurityConfig.loginFailureHandler or failureUrl)
    // useEffect(() => {
    //     try {
    //         const params = typeof window !== 'undefined' ? new URLSearchParams(window.location.search) : null;
    //         if (params) {
    //             if (params.has('incorrect_email_or_password')) {
    //                 setError('Tên đăng nhập hoặc mật khẩu không đúng');
    //             } else if (params.has('account_disabled')) {
    //                 setError('Tài khoản đã bị vô hiệu hóa');
    //             } else if (params.has('unknown_error')) {
    //                 setError('Đã xảy ra lỗi không xác định. Vui lòng thử lại');
    //             } else if (params.has('error')) {
    //                 // generic Spring Security failure URL uses ?error
    //                 setError('Đăng nhập thất bại. Vui lòng kiểm tra thông tin và thử lại');
    //             }
    //             // optional: remove querystring so refresh won't re-show the message
    //             // window.history.replaceState({}, '', window.location.pathname);
    //         }
    //     } catch (e) {
    //         // ignore client-side parse errors
    //     }
    // }, []);

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
        <div style={{
            width: '100%', height: '100vh', display: 'flex',
            alignItems: 'center', justifyContent: 'center',
            background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
        }}>
            <div style={{
                background: 'white', borderRadius: '16px',
                boxShadow: '0 20px 60px rgba(0,0,0,0.3)',
                padding: '40px 60px',
                boxSizing: 'border-box',
                maxWidth: '400px', width: '100%', textAlign: 'center',
            }}>
                <h1 style={{ color: '#667eea', marginBottom: '10px', fontSize: '2rem', fontWeight: 'bold' }}>
                    Đăng nhập
                </h1>
                {error && (
                    <p style={{ color: 'red', marginBottom: '16px', fontSize: '0.9rem' }}>{error}</p>
                )}
                <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column' }}>
                    <input
                        name="username"
                        type="text"
                        placeholder="Tên đăng nhập"
                        required
                        style={{
                            padding: '12px 20px', marginBottom: '20px',
                            borderRadius: '8px', border: '1px solid #ccc', fontSize: '1rem',
                        }}
                    />
                    <input
                        name="password"
                        type="password"
                        placeholder="Mật khẩu"
                        required
                        style={{
                            padding: '12px 20px', marginBottom: '30px',
                            borderRadius: '8px', border: '1px solid #ccc', fontSize: '1rem',
                        }}
                    />
                    <button type="submit" style={{
                        padding: '12px 20px', background: '#667eea', color: 'white',
                        border: 'none', borderRadius: '8px', fontSize: '1rem', cursor: 'pointer',
                    }}>
                        Đăng nhập
                    </button>
                </form>
            </div>
        </div>
    );
}