import {useState} from "react";
import {useNavigate, useSearchParams} from "react-router";

export {SearchBar as SearchBar};

const SearchBar = () => {
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();
    const [searchQuery, setSearchQuery] = useState(searchParams.get('query') || '');

    const handleSearch = async () => {
        if (searchQuery.trim()) {
            navigate(`/search?query=${encodeURIComponent(searchQuery.trim())}`);
        }
    };

    const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
        if (e.key === 'Enter') {
            handleSearch();
        }
    };


    return (
    <div
        style={{
            maxWidth: '1000px',
            margin: '0 auto 20px',
            background: 'white',
            borderRadius: '8px',
            boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
            padding: '20px',
            display: 'flex',
            gap: '10px',
        }}
    >
        <input
            type="text"
            placeholder="Nhập ký tự chữ Nôm..."
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            onKeyDown={handleKeyDown}
            style={{
                flex: 1,
                fontSize: '16px',
                padding: '10px 15px',
                border: '1px solid #ccc',
                borderRadius: '4px',
                outline: 'none',
                width: '100%',
            }}
        />
        <button
            onClick={handleSearch}
            style={{
                padding: '10px 30px',
                fontSize: '16px',
                background: '#667eea',
                color: 'white',
                border: 'none',
                borderRadius: '4px',
                cursor: 'pointer',
                fontWeight: '500',
            }}
            onMouseEnter={(e: React.MouseEvent<HTMLButtonElement>) => {
                e.currentTarget.style.background = '#5568d3';
            }}
            onMouseLeave={(e: React.MouseEvent<HTMLButtonElement>) => {
                e.currentTarget.style.background = '#667eea';
            }}
        >
            Tìm Kiếm
        </button>
    </div>
    );
};