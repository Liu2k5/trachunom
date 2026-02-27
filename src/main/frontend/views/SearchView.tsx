import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import {useEffect, useState} from 'react';
import { useSearchParams } from 'react-router';
import { findByQuery } from 'Frontend/generated/SearchEndpoint';
import {color} from "@vaadin/vaadin-lumo-styles";
import {EntityService} from "Frontend/generated/endpoints";
import EntityX from "Frontend/generated/com/liu/trachunom/entity/EntityX";
import {HnomQnguComponent} from "Frontend/utils/entityUtils";

export const config: ViewConfig = {
  menu: { order: 1, icon: 'la la-search' },
  title: 'Tìm Chữ',
  route: 'search',
};

export default function SearchView() {
  const [searchParams, setSearchParams] = useSearchParams();
  const [searchQuery, setSearchQuery] = useState(searchParams.get('query') || '');
  const [searchResults, setSearchResults] = useState<any[]>([]);

  const handleSearch = async () => {
    if (searchQuery.trim()) {
      setSearchParams({ query: searchQuery.trim() });
        try {
            const results = await findByQuery(searchQuery);
            setSearchResults(results || []);
        } catch (error) {
            console.error('Search error:', error);
            setSearchResults([]);
        }
    }
  };

  const handleKeyPress = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      handleSearch();
    }
  };

    useEffect(() => {
        handleSearch();
    }, []);

  return (
    <div
      style={{
        width: 'auto',
        minHeight: '100vh',
        background: '#f5f5f5',
        padding: '20px',
      }}
    >
      {/* Search Bar */}
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
          onKeyPress={handleKeyPress}
          style={{
            flex: 1,
            fontSize: '16px',
            padding: '10px 15px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            outline: 'none',
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

      {/* Content Area */}
      <div
        style={{
          maxWidth: '1000px',
          margin: '0 auto',
          background: 'white',
          borderRadius: '8px',
          boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
          padding: '30px',
          minHeight: '400px',
        }}
      >
        {searchParams.get('query') ? (
          <div>
            <h2 style={{ color: '#333', marginBottom: '20px' }}>
              Kết quả tìm kiếm cho: "{searchParams.get('query')}"
            </h2>
            {searchResults.length > 0 ? (
              <div>
                {/* Display search results here */}
                {searchResults.map((result, index) => <ResultContent result={result} index={index}/>)}
              </div>
            ) : (
              <p style={{ color: '#666', fontSize: '16px' }}>
                Không tìm thấy kết quả nào.
              </p>
            )}
          </div>
        ) : (
          <div style={{ textAlign: 'center', padding: '40px 0' }}>
            <h2 style={{ color: '#667eea', marginBottom: '10px' }}>
              Tra Cứu Chữ Nôm
            </h2>
            <p style={{ color: '#666', fontSize: '16px' }}>
              Nhập ký tự chữ Nôm vào ô tìm kiếm để bắt đầu tra cứu.
            </p>
          </div>
        )}
      </div>
    </div>
  );
}


const ResultContent = ({ result, index }: { result: EntityX, index: number }) => {
    let hnomString;
    let qnguString;

    // useEffect(() => {
    //     EntityService.getHnomStringByExample(result).then((hnom) => hnomString = hnom);
    //     EntityService.getQnguStringByExample(result).then((qngu) => qnguString = qngu);
    // }, []);

    return (
        <a
            key={index}
            style={{
                padding: '15px',
                borderBottom: '1px solid #eee',
                textDecoration: 'none',
                color: '#333',
            }}
            href={"/entity/" + result.id}
        >
            <div>
                        <span style={{
                            fontSize: '24px',
                            display: 'inline-flex',
                            alignItems: 'end',
                        }}>
                            <span style={{
                                color: 'white',
                                backgroundColor: 'red',
                                padding: '2px 6px',
                                borderRadius: '4px',
                                marginRight: '10px',
                                fontSize: '20px',
                                fontWeight: 'bold',
                            }}>
                                {result.language?.abbreviation ?? 'N/A'}
                            </span>
                            <span>
                                <HnomQnguComponent entityId={result.id} markedId={0}/>
                            </span>
                        </span>
            </div>
            <div>
                <span>{result.explanationsString}</span>
            </div>
        </a>
    );
};
