import {useState} from "react";
import {useNavigate, useSearchParams} from "react-router";
import {correct} from "Frontend/utils/inputUtils";
import {Button, TextField} from "@vaadin/react-components";
import {DisplayTroubleshooter} from "Frontend/utils/displayTroubleshooter";

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

    const [isDialogOpened, setDialogOpened] = useState(false);


    return (
    <>
        <div
            style={{
                position: 'fixed',
                top: 0,
                left: 0,
                right: 0,
                width: '100vw',
                backgroundColor: 'var(--lumo-base-color)',
                zIndex: 1000,
                boxSizing: 'border-box',
                borderBottom: '1px solid var(--lumo-contrast-10pct)',
            }}
        >
            <div
                style={{
                    display: 'flex',
                    gap: 'var(--lumo-space-m)',
                    alignItems: 'center',
                    maxWidth: '1200px',
                    margin: '0 auto',
                    padding: 'var(--lumo-space-s) var(--lumo-space-m)',
                    boxSizing: 'border-box',
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
                    placeholder="trời 𡗶 天上"
                    value={searchQuery}
                    onInput={(e: any) => setSearchQuery(e.target.value)}
                    onKeyDown={handleKeyDown}
                    style={{ width: '100%', minWidth: '100px' }}
                    clearButtonVisible
                />
                <Button
                    theme="secondary"
                    onClick={() => setDialogOpened(!isDialogOpened)}
                >
                    空𧡊字？
                </Button>
            </div>
        </div>
        <div>
            {isDialogOpened && (<DisplayTroubleshooter onClose={() => setDialogOpened(false)} />)}
        </div>
    </>
    );
};