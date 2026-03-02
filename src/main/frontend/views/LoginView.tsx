import { ViewConfig } from "@vaadin/hilla-file-router/types.js";
import { useState } from 'react';

export const config: ViewConfig = {
    route: 'login',
    menu: { exclude: true },
    title: 'Đăng nhập',
};

export default function LoginView() {
    const [error, setError] = useState('');

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
                },
                body: body.toString(),
                credentials: 'same-origin',
                redirect: 'manual',
            });

            // Spring Security trả về 302 khi đăng nhập thành công
            // type === 'opaqueredirect' khi redirect: 'manual' và có redirect
            if (response.type === 'opaqueredirect' || response.ok) {
                window.location.href = '/';
            } else {
                setError('Tên đăng nhập hoặc mật khẩu không đúng');
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