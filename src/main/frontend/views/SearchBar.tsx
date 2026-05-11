import {useState} from "react";
import {useNavigate, useSearchParams} from "react-router";
import {correct} from "Frontend/utils/inputUtils";
import {Button, TextField} from "@vaadin/react-components";

export {SearchBar as SearchBar};

const SearchBar = () => {
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();
    const [searchQuery, setSearchQuery] = useState(searchParams.get('query') || '');

    const handleSearch = (queryToSearch: string) => {
        if (queryToSearch.trim()) {
            navigate(`/search?query=${encodeURIComponent(correct(queryToSearch).trim())}`);
        }
    };

    const handleKeyDown = (e: any) => {
        if (e.key === 'Enter') {
            const currentVal = e.target.value;
            setSearchQuery(currentVal);
            handleSearch(currentVal);
        }
    };


    return (
    <div
        style={{
            display: 'flex',
            gap: 'var(--lumo-space-m)',
            alignItems: 'center',
        }}
    >
        <img
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/N%C3%B4m_1.png/960px-N%C3%B4m_1.png"
            alt="Trang Chủ"
            onClick={() => navigate('/')}
            style={{ height: '40px', cursor: 'pointer', objectFit: 'contain' }}
            title="Về Trang Chủ"
        />
        <TextField
            placeholder="Nhập ký tự chữ Nôm..."
            value={searchQuery}
            onInput={(e: any) => setSearchQuery(e.target.value)}
            onKeyDown={handleKeyDown}
            style={{ flex: 1 }}
            clearButtonVisible
        />
        <Button
            theme="primary"
            onClick={() => handleSearch(searchQuery)}
        >
            Tìm Kiếm
        </Button>
    </div>
    );
};