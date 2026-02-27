import {ViewConfig} from '@vaadin/hilla-file-router/types.js';
import {useParams, useNavigate} from 'react-router';
import {useEffect, useState} from 'react';
import type EntityDetailDto from 'Frontend/generated/com/liu/trachunom/dto/EntityDetailDto';
import type StructureDto from "Frontend/generated/com/liu/trachunom/dto/StructureDto";
import {getEntityDetail} from 'Frontend/generated/EntityDetailEndpoint';
import {getStructureDtoByStructureId} from 'Frontend/generated/EntityDetailEndpoint';
import {getEntityEvolutionsByToEntityId} from 'Frontend/generated/EntityDetailEndpoint';
import {getGridTemplateColumnsByStructureId} from 'Frontend/generated/EntityDetailEndpoint';
import * as StructureEndpoint from 'Frontend/generated/StructureEndpoint';
import StructureComponentDto from "Frontend/generated/com/liu/trachunom/dto/StructureComponentDto";
import EntityEvolutionDto from "Frontend/generated/com/liu/trachunom/dto/EntityEvolutionDto";
import EntityDto from "Frontend/generated/com/liu/trachunom/dto/EntityDto";
import EntityX from "Frontend/generated/com/liu/trachunom/entity/EntityX";
import {EntityService} from "Frontend/generated/endpoints";
import {
    DrawEvolution,
    DrawMeaningEvolution,
    DrawPronunciationEvolution,
    DrawStructure,
    HnomQnguComponent
} from 'Frontend/utils/entityUtils';

export const config: ViewConfig = {
    title: 'Chi Tiết Thực Thể',
    route: 'entity/:id',
};


export default function EntityDetailView() {
    const {id} = useParams<{ id: string }>();
    const navigate = useNavigate();
    const [entity, setEntity] = useState<EntityDetailDto | null | undefined>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // TODO: Fetch entity data from backend
        // Example: EntityService.getById(id)
        // Mock data for demonstration
        getEntityDetail(Number(id)).then((data) => {
            setEntity(data);
            setLoading(false);
        }).catch((error) => {
            console.error('Error fetching entity:', error);
            setLoading(false);
        });
    }, [id]);

    if (loading) {
        return (
            <div
                style={{
                    width: '100%',
                    minHeight: '100vh',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    background: '#f5f5f5',
                }}
            >
                <div style={{fontSize: '18px', color: '#666'}}>Đang tải...</div>
            </div>
        );
    }

    if (!entity) {
        return (
            <div
                style={{
                    width: '100%',
                    minHeight: '100vh',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    background: '#f5f5f5',
                }}
            >
                <div style={{textAlign: 'center'}}>
                    <h2 style={{color: '#666'}}>Không tìm thấy thực thể</h2>
                    <button
                        onClick={() => navigate('/search')}
                        style={{
                            marginTop: '20px',
                            padding: '10px 20px',
                            background: '#667eea',
                            color: 'white',
                            border: 'none',
                            borderRadius: '4px',
                            cursor: 'pointer',
                        }}
                    >
                        Quay lại tìm kiếm
                    </button>
                </div>
            </div>
        );
    }

    return (
        <div
            style={{
                width: 'auto',
                minHeight: '100vh',
                background: '#f5f5f5',
                padding: '20px',
            }}
        >
            <div
                style={{
                    maxWidth: '1000px',
                    margin: '0 auto',
                }}
            >
                {/* Back button */}
                <button
                    onClick={() => navigate(-1)}
                    style={{
                        marginBottom: '20px',
                        padding: '8px 16px',
                        background: 'white',
                        border: '1px solid #ddd',
                        borderRadius: '4px',
                        cursor: 'pointer',
                        fontSize: '14px',
                    }}
                >
                    ← Quay lại
                </button>

                {/* Main content */}
                <div
                    style={{
                        background: 'white',
                        borderRadius: '8px',
                        boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
                        padding: '40px',
                    }}
                >
                    {/* Character display */}
                    <div
                        style={{
                            textAlign: 'center',
                            marginBottom: '40px',
                            paddingBottom: '30px',
                            borderBottom: '2px solid #f0f0f0',
                        }}
                    >
                        {!entity.compound ? (
                                <>
                                    <div
                                        style={{
                                            fontSize: '80px',
                                            fontWeight: 'bold',
                                            color: '#667eea',
                                            marginBottom: '20px',
                                            fontFamily: 'serif',
                                            display: 'flex',
                                            justifyContent: 'center',
                                            gap: '20px',
                                        }}
                                    >
                                        <HnomQnguComponent entityId={entity.id} markedId={0}/>
                                    </div>
                                    {entity.structure?.character && (
                                        <div>
                                            <p>Unicode: U+{(entity.structure?.character?.characterString?.charCodeAt(0))?.toString(16).toUpperCase()}</p>
                                            <p>Bộ {entity.structure?.character?.radicalString}
                                                + {entity.structure?.character?.additionalStrokeNumber} nét,
                                                tổng {entity.structure?.character?.totalStrokeNumber}
                                            </p>
                                        </div>
                                    )}
                                </>
                            )
                            :
                            <>
                                <div
                                    style={{
                                        fontSize: '80px',
                                        fontWeight: 'bold',
                                        color: '#667eea',
                                        marginBottom: '20px',
                                        fontFamily: 'serif',
                                        display: 'flex',
                                        justifyContent: 'center',
                                        gap: '20px',
                                    }}
                                >
                                    {entity.compositionComponents?.map((component, index) => (
                                        <div key={index}>
                                            {component ? (
                                                <HnomQnguComponent entityId={component.id} markedId={0}/>
                                            ) : (
                                                <div style={{color: 'grey'}}>không có</div>
                                            )}
                                        </div>
                                    ))}
                                </div>
                                <div>
                                    <p>{entity.pronunciation?.pronunciationString}</p>
                                </div>
                            </>
                        }
                    </div>

                    {/* Structure */}
                    {entity.structure && (
                        <section style={{marginBottom: '30px'}}>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Cấu tạo
                            </h2>
                            <div style={{width: '100%',}}>
                                <DrawStructure structure={entity.structure}/>
                            </div>
                        </section>
                    )}

                    {/* Pronunciation */}
                    {entity.pronunciation && (
                        <section style={{marginBottom: '30px'}}>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Phát âm
                            </h2>
                            <div style={{paddingLeft: '16px'}}>
                                <p style={{fontSize: '18px', color: '#333'}}>
                                    <DrawPronunciationEvolution pronunciationId={entity.pronunciation.id}/>
                                </p>
                            </div>
                        </section>
                    )}

                    {/* Meanings */}
                    {entity.meaning?.explanations && entity.meaning.explanations.length > 0 && (
                        <section style={{marginBottom: '30px'}}>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Ý nghĩa
                            </h2>
                            <DrawMeaningEvolution meaning={entity.meaning}/>
                        </section>
                    )}

                    {/* Evolution */}
                    {entity.evolutions && (
                        <section style={{marginBottom: '30px'}}>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Quá trình phát triển
                            </h2>
                            <div style={{paddingLeft: '16px'}}>
                                <div>
                                    <p>{entity.hnomString} {entity.qnguString}</p>
                                    <p>{entity.explanationsString}</p>
                                </div>
                                <DrawEvolution evolutions={entity.evolutions?.filter((evolution): evolution is EntityEvolutionDto => evolution !== undefined)} />
                            </div>
                        </section>
                    )}

                    {/* Variances */}
                    {entity.variances && entity.variances.length > 0 && (
                        <section style={{marginBottom: '30px'}}>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Biến thể
                            </h2>
                            <div style={{paddingLeft: '16px'}}>
                                {entity.variances.map(variance =>
                                    variance ? (
                                        <div
                                            key={variance.id}
                                            style={{
                                                marginBottom: '10px',
                                                fontSize: '18px',
                                            }}
                                        >
                                            <HnomQnguComponent entityId={variance.id} markedId={0}/>
                                        </div>
                                    ) : null
                                )}
                            </div>
                        </section>
                    )}

                    {/* Synonyms */}
                    {entity.synonyms && entity.synonyms.length > 0 && (
                        <section style={{marginBottom: '30px'}}>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Từ đồng nghĩa
                            </h2>
                            <div style={{paddingLeft: '16px'}}>
                                {entity.synonyms?.map(synonym =>
                                    synonym ? (
                                        <div
                                            key={synonym.id}
                                            style={{
                                                marginBottom: '10px',
                                                fontSize: '18px',
                                            }}
                                        >
                                            <HnomQnguComponent entityId={synonym.id} markedId={0}/>
                                        </div>
                                    ) : null
                                )}
                            </div>
                        </section>
                    )}

                    {/* Examples */}
                    {entity.examples && entity.examples.length > 0 && (
                        <section>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Ví dụ
                            </h2>
                            {entity?.examples ? (
                                    entity.examples.map(example =>
                                        <>
                                            <div style={{
                                                display: 'flex',
                                                gap: '10px',
                                                marginBottom: '20px',
                                                alignItems: 'end',
                                            }}>
                                                <div
                                                    style={{
                                                        display: 'flex',
                                                        flexWrap: 'wrap',
                                                        gap: '10px',
                                                        marginBottom: '10px',
                                                        fontFamily: 'sans-serif',
                                                        width: 'fit-content',
                                                    }}>
                                                    {
                                                        example?.exampleWords ? (
                                                            example.exampleWords.map(word => {
                                                                    if (word?.entity) {
                                                                        return (
                                                                            <HnomQnguComponent entityId={word.entity.id} markedId={entity.id ?? 0}/>
                                                                        );
                                                                    }
                                                                    return undefined;
                                                                }
                                                            )
                                                        ) : <div>khong co example</div>
                                                    }
                                                    <p
                                                        title={example ? example.sourceDescription : ''}
                                                        style={{
                                                            fontFamily: 'sans-serif',
                                                            fontStyle: 'italic',
                                                            color: '#666',
                                                            fontSize: '0.8em',
                                                        }}
                                                    >
                                                        {example ? example.sourceName : ''}
                                                    </p>
                                                </div>
                                            </div>
                                        </>
                                    )
                                )
                                : (
                                    <p style={{color: '#666', fontSize: '16px'}}>
                                        Không tìm thấy kết quả nào.
                                    </p>
                                )
                            }
                        </section>
                    )}
                </div>
            </div>
        </div>
    );
}
